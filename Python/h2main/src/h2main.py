##
## Author: Max Rogers
## Date: 02/10/2013
import sys
import codecs
def main(argv):
    if (argv == None):
        print("Null")
    print ("hello world")
    f=codecs.open(argv, "r", encoding='utf-8')
    sfile=f.read()
    print type(sfile)
    print sfile.encode('utf-8')
    print type(sfile.encode('utf-8'))
main(sys.argv[1:])