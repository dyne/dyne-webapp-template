#!/bin/sh

rm -rf testproj \
	&& lein new dyne-webapp testproj \
	&& cd testproj
lein ring server
cd ..
