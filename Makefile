# Simple Makefile to compile Assignment 1 source files.
# Adapted from file uploaded to Vula site CSC2001F(2019) by P Marais.

CC=javac

BINDIR=./bin
SRCDIR=./src
DOCDIR=./doc

.SUFFIXES: .java .class

default: all

# General build rule: .java => .class
${BINDIR}/%.class: ${SRCDIR}/%.java
	javac $< -cp ${BINDIR} -d ${BINDIR}

# Build dependency rules
${BINDIR}/MyFiles.class: ${SRCDIR}/MyFiles.java
${BINDIR}/PointElevation.class: &{SRCDIR}/PointElevation.java
${BINDIR}/ElevationAnalysis.class: ${SRCDIR}/ElevationAnalysis.java ${BINDIR}/PointElevation.class
${BINDIR}/TerrainClassify.class: ${SRCDIR}/TerrainClassify.java ${BINDIR}/ElevationAnalysis.class ${BINDIR}/MyFiles.class

all: clean clean-docs compile docs

compile:
	javac -d ${BINDIR} ${SRCDIR}/*.java

docs:
	javadoc  -cp ${BINDIR} -d ${DOCDIR} ${SRCDIR}/*.java

clean:
	rm -f ${BINDIR}/*.class

clean-docs:
	rm -rf ${DOCDIR}/*

.PHONY: default all compile docs clean clean-docs
