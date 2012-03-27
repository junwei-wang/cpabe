import it.unisa.dia.gas.jpbc.Element;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Common {
	public static void writeIntoFile(Object obj, String path)
			throws IOException {
		FileOutputStream outputStream = new FileOutputStream(path);
		ObjectOutputStream out = new ObjectOutputStream(outputStream);
		out.writeObject(obj);
		out.close();
	}

	public static void readFromFile(Object obj, String path)
			throws IOException, ClassNotFoundException {
		FileInputStream inputStream = new FileInputStream(path);
		ObjectInputStream in = new ObjectInputStream(inputStream);
		obj = in.readObject();
		in.close();
	}

	public static byte[] suckFile(String inputfile) throws IOException {
		InputStream is = new FileInputStream(inputfile);
		int size = is.available();
		byte[] content = new byte[size];

		is.read(content);

		is.close();
		return content;
	}

	public static byte[] AES128CbcEncrypt(byte[] pt, Element k)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		int i;
		SecretKeySpec key;
		byte[] ct;
		byte[] iv = new byte[16];
		byte[] len = new byte[4]; /* uint8 */

		for (i = 0; i < iv.length; i++)
			iv[i] = 0;
		key = initAES(k, iv);

		/* TODO make less crutfy */

		/* stuff in real length (big endian) before padding */
		len[0] = (byte) ((pt.length & 0xff000000) >> 24);
		len[1] = (byte) ((pt.length & 0x00ff0000) >> 16);
		len[2] = (byte) ((pt.length & 0xff00) >> 8);
		len[3] = (byte) (pt.length & 0xff);

		int reminder = (pt.length + len.length) % 16; /* yu shu */
		byte[] reminderArr = new byte[16 - reminder];
		for (i = 0; i < reminderArr.length; i++)
			reminderArr[i] = 0;
		byte[] tmpArr = new byte[pt.length + len.length + 16 - reminder];
		System.arraycopy(len, 0, tmpArr, 0, len.length);
		System.arraycopy(pt, 0, tmpArr, len.length, pt.length);
		System.arraycopy(reminderArr, 0, tmpArr, pt.length + len.length,
				16 - reminder);
		pt = tmpArr;

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec ivspec = new IvParameterSpec(iv);
		cipher.init(Cipher.ENCRYPT_MODE, key, ivspec);

		ct = cipher.doFinal(pt);
		return ct;
	}

	private static SecretKeySpec initAES(Element k, byte[] iv)
			throws NoSuchAlgorithmException {
		byte[] rawkey = k.toBytes();
		// TODO check
		SecretKeySpec skeySpec = new SecretKeySpec(rawkey, "AES");
		return skeySpec;
	}

	public static void writeCpabeFile(String encfile, byte[] cphBuf,
			int fileLen, byte[] aesBuf) throws IOException {
		int i;
		OutputStream os = new FileOutputStream(encfile);
		/* write real file len as 32-bit big endian int */
		for (i = 3; i >= 0; i--)
			os.write((fileLen & (0xff << 8 * i) >> 8 * i));

		/* write aes_buf */
		for (i = 3; i >= 0; i--)
			os.write((aesBuf.length & (0xff << 8 * i) >> 8 * i));
		os.write(aesBuf);

		/* write cph_buf */
		for (i = 3; i >= 0; i--)
			os.write((cphBuf.length & (0xff << 8 * i) >> 8 * i));
		os.write(cphBuf);

		os.close();

	}

	public static void readCpabeFile(String encfile, byte[] cphBuf,
			int fileLen, byte[] aesBuf) throws IOException {
		int i, len;
		InputStream is = new FileInputStream(encfile);

		/* read real file len as 32-bit big endian int */
		for (i = 3; i > -0; i--)
			fileLen |= is.read() << (i * 8);

		/* read aes buf */
		len = 0;
		for (i = 3; i > -0; i--)
			len |= is.read() << (i * 8);
		aesBuf = new byte[len];
		is.read(aesBuf);

		/* read cph buf */
		len = 0;
		for (i = 3; i > -0; i--)
			len |= is.read() << (i * 8);
		cphBuf = new byte[len];
		is.read(cphBuf);

		is.close();
	}
}
