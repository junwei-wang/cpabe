prefix = graduation_design
depend = $(prefix).tex abstract.tex titlepage.tex introduction.tex related_work.tex \
	 background.tex construction.tex implementation.tex appendix.tex $(prefix).bib\
	 Setup.java Keygen.java Enc.java Dec.java DemoForCpabe.java
all: $(prefix).pdf
$(prefix).pdf: $(depend)
	xelatex $(prefix).tex 
	bibtex $(prefix)
	xelatex $(prefix).tex 
	xelatex $(prefix).tex 
	- rm *.aux
	- rm *.toc
	- rm *.log
	- rm *.dvi
	- rm *.bbl
	- rm *.blg
	- rm *.out
clean:
	- rm *.pdf
