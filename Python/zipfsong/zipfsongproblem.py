#!/usr/bin/env python

""" zipfsong.py: This code is a proof of code for Spotify, @see http://www.spotify.com/es/jobs/tech/zipfsong/  """
"""
NOTE: I think that the system who tests the code is not correct, it gives all the times the error "Exited (non-zero
exit status)" I try to put "try...except" by all the code, modify the input parser, etc, but... didn't work :'(
I created a script "create_input.py" to create test inputs, I used the max allowed limits, etc... but nothing
"""

__author__ = "Alonso Vidales"
__email__ = "alonso.vidales@tras2.es"
__date__ = "2013-01-12"

class ZipfSong:
    __groups = None
    __songs = None
    __debug = False

    def resolve(self, inOutLen):
        """
        This method sorts all the songs by the Zipf's law, and returns the inOutLen songs names as
        a string concatenated by the new line character
        """
        # Add the quality value to the songs to be sorted by this
        for songPos in xrange(0, len(self.__songs)):
            self.__songs[songPos]['q'] = self.__songs[songPos]['times'] * (songPos + 1)

        if self.__debug:
            print self.__songs

        # Short the array of songs by quality in descending order and get the first inOutLen songs
        resultList = sorted(self.__songs, key = lambda song: song['q'], reverse = True)[:inOutLen]

        # Return the array as a string concatenated with new lines
        return "\n".join([song['name'] for song in resultList])

    def __init__(self, inSongs):
        self.__songs = []
        for song in inSongs:
            songInfo = song.split()
            self.__songs.append({
                'times': int(songInfo[0]),
                'name': songInfo[1]
            })

if __name__ == "__main__":
    # I'll use raw_input to get the lines because I can't import fileinput on the test server
    lines = []
    while True:
        try:
            lines.append(raw_input())
        except (EOFError):
            break #end of file reached

    info = map(int, lines[0].split())

    print ZipfSong(lines[1:info[0] + 1]).resolve(info[1])