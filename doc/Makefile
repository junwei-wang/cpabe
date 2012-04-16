prefix = graduation_design
all: $(prefix).pdf
$(prefix).pdf: $(prefix).tex abstract.tex titlepage.tex
	xelatex $(prefix).tex 
	bibtex $(prefix)
	xelatex $(prefix).tex 
	xelatex $(prefix).tex 
clean:
	- rm *.pdf
	- rm *.aux
	- rm *.toc
	- rm *.log
	- rm *.dvi
	- rm *.bbl
	- rm *.blg
