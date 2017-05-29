#!/bin/bash

#read all mp4 files, convert them to mp3 and save in current folder

for file in *.mp4; do /Applications/VLC.app/Contents/MacOS/VLC -I dummy "$file" --sout="#transcode{acodec=mp3,vcodec=dummy}:standard{access=file,mux=raw,dst=\"$(echo "$file" | sed 's/\.[^\.]*$/.mp3/')\"}" vlc://quit; done
