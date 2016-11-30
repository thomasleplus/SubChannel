# SubChannel - A study on subliminal channels in DSA algorithm.
# Copyright (C) 2016 Thomas Leplus
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.

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
