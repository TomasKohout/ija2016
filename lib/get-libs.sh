#/bin/bash
wget http://www.stud.fit.vutbr.cz/~xkohou08/images.zip
unzip images.zip
rm images.zip
mkdir ../examples
mv odevzdani.rebus ../examples
mv -v images ../src
