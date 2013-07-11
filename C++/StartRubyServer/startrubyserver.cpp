#include <stdio.h>   /* printf, stderr, fprintf */
#include <sys/types.h> /* pid_t */
#include <unistd.h>  /* _exit, fork */
#include <stdlib.h>  /* exit */
#include <errno.h>   /* errno */
 
int main(void)
{
   pid_t  pid;
 
   /* Output from both the child and the parent process
    * will be written to the standard output,
    * as they both run at the same time.
    */
   pid = fork();
 
   if (pid == -1)
   {
      /* Error:
       * When fork() returns -1, an error happened
       * (for example, number of processes reached the limit).
       */
      fprintf(stderr, "can't fork, error %d\n", errno);
      exit(EXIT_FAILURE);
   }
   else if (pid == 0)
   {
      /* Child process:
       * When fork() returns 0, we are in
       * the child process.
       */
      int  j;
      for (j = 0; j < 4; j++)
      {
         printf("child: %d\n", j);
         	if(j == 1){
         		system("memcached -vv");
         	}
         	if(j == 1){
         		system("cassandra-cli");
         	}
         	if(j == 2){
         		system("elasticsearch -f");
         	}
         	if(j == 3){
         		system("rails server");
         	}
         sleep(1);
      }
      _exit(0);  /* Note that we do not use exit() */
   }
   else
   {
      /* When fork() returns a positive number, we are in the parent process
       * (the fork return value is the PID of the newly created child process)
       * Again we count up to ten.
       */
      int  i;
      for (i = 0; i < 4; i++)
      {
         printf("parent: %d\n", i);
         sleep(1);
      }
      exit(0);
   }
   return 0;
}