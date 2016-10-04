# Changer la ligne suivante
PROJECT = rapport

# --------------------------------------------------------------------------- #

all: $(PROJECT).dvi $(PROJECT).ps $(PROJECT).pdf

%.dvi: %.tex
	latex $*
	while grep -q "Rerun to get cross-references right." $*.log ; \
	do \
		latex $* ; \
	done
	if [[ -f $*.idx ]] ; \
	then \
		makeindex $* ; \
		latex $* ; \
	fi
	if [[ -f $*.bib ]] && grep -q "There were undefined references." $*.log ; \
	then \
		bibtex $* ; \
		latex $* ; \
		latex $* ; \
	fi

%.ps : %.dvi
	dvips $< -o $@

%.pdf : %.ps
	ps2pdf $<

clean:
	rm -f \
	*.aux \
	*.bak \
	*.bbl \
	*.blg \
	*.idx \
	*.ind \
	*.ilg \
	*.log \
	*.lof \
	*.toc \
	core* \
	*~
