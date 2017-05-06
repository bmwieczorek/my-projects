#!/bin/bash
originalFileName=${1}
newFileName=$(./remove-polish-chars.sh "${originalFileName}")

if ! [ "${originalFileName}" == "${newFileName}" ]; then
    echo "Removed file \"${originalFileName}\" to \"${newFileName}\""
    mv "${originalFileName}" "${newFileName}"
fi

