#/bin/bash
wget http://www.stud.fit.vutbr.cz/~xkohou08/images.zip
unzip images.zip
rm images.zip
mkdir ../save
mv odevzdani.rebus ../save
mv -v images ../src
