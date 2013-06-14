#!/usr/bin/env python

import random

samples = 1
catsDogs = 4
voters = 10

print samples
for sample in xrange(0, samples):
    print "%s %s %s" % (catsDogs, catsDogs, voters)
    for voter in xrange(0, voters):
        if random.randint(0, 1) == 1:
            print "C%s D%s" % (random.randint(0, catsDogs), random.randint(0, catsDogs))
        else:
            print "D%s C%s" % (random.randint(0, catsDogs), random.randint(0, catsDogs))