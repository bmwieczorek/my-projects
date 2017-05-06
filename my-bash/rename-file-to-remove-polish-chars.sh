#!/bin/bash
originalFileName=${1}
newFileName=$(echo ${1} |\
    sed 's/Ą/A/g' |\
    sed 's/ą/a/g' |\
    sed 's/Ć/C/g' |\
    sed 's/ć/c/g' |\
    sed 's/ę/e/g' |\
    sed 's/Ł/L/g' |\
    sed 's/ł/l/g' |\
    sed 's/ń/n/g' |\
    sed 's/ó/o/g' |\
    sed 's/Ś/S/g' |\
    sed 's/ś/s/g' |\
    sed 's/Ż/Z/g' |\
    sed 's/ż/z/g' |\
    sed 's/Ź/Z/g' |\
    sed 's/ź/z/g' |\
    sed 's/ą/a/g' |\
    sed 's/ć/c/g' |\
    sed 's/ę/e/g' |\
    sed 's/ł/l/g' |\
    sed 's/ł/l/g' |\
    sed 's/ń/n/g' |\
    sed 's/ó/o/g' |\
    sed 's/ś/s/g' |\
    sed 's/Ż/Z/g' |\
    sed 's/ż/z/g' )

if ! [ "${originalFileName}" == "${newFileName}" ]; then
    echo "Renamed file \"${originalFileName}\" to \"${newFileName}\""
    mv "${originalFileName}" "${newFileName}"
fi

