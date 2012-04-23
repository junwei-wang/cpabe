prefix = graduation_design
depend = $(prefix).tex abstract.tex titlepage.tex introduction.tex \
	 related_work.tex background.tex construction.tex \
	 $(prefix).bib
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
clean:
	- rm *.pdf
	- rm *.aux
	- rm *.toc
	- rm *.log
	- rm *.dvi
	- rm *.bbl
	- rm *.blg
