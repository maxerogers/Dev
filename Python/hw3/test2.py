'''
Brute-force, backtracking Sudoku solver in about fifteen lines.

Works on Python 2.6 and Python 3.
'''
def solve(s):
    ''' 
    Solve a Sudoku:

    - Accepts s, a sequence of 36 integers from 0 to 6 in row of
    column order, zeros indicating the cells to fill.

    - Returns the first found solution as a sequence of 36 integers in
    the 1 to 6 interval (same row or column order than input), or None
    if no solution exists.
    '''
    try:
        i  = s.index(0)
    except ValueError: 
        # No empty cell left: solution found
        return s

    c = [s[j] for j in range(36)
         if not ((i-j)%6 * (i//6^j//6) * (i//18^j//18 | (i%6//3^j%6//3)))]

    for v in range(1, 7):
        if v not in c:
            r = solve(s[:i]+[v]+s[i+1:])
            if r is not None:
                return r

#-------------------------------------------------------------------------------
# Let's test it!
#
if __name__ == '__main__':
    class Sudoku(list):
        '''Sudokus with nicer IO'''
        def __init__(self, content):
            list.__init__(self, [int(i) for i in content.split()] 
                          if isinstance(content, str) else content)
        def __str__(self):
            return '\n'.join(
                ' '.join([(str(j) if j != 0 else '-') 
                          for j in self[i*6:(i+1)*6]]) for i in range(6))

    problem = Sudoku('''
        6 0 4 2 3 5
        5 2 3 0 1 4
        2 4 0 5 0 3
        3 6 5 4 2 1
        0 5 2 3 4 0
        4 3 6 1 5 2 
        ''')

    solution = Sudoku('''
        6 1 4 2 3 5
        5 2 3 6 1 4
        2 4 1 5 6 3
        3 6 5 4 2 1
        1 5 2 3 4 6
        4 3 6 1 5 2 
        ''')

    result = Sudoku(solve(problem))

    print('==== Problem ====\n{0}\n\n=== Solution ====\n{1}'.format(
            problem, result))
    
    assert(result == solution)
