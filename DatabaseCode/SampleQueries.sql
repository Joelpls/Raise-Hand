/** This file is to help group members access the data by giving them common SQL queries 
Written by Stephanie Engelhardt on 09/15/2017 **/

/* This query selects all of the threads and their replies and joins them together
to allow access to both of them. */
SELECT thread.*, reply.* FROM threads thread
LEFT OUTER JOIN replies reply ON thread.ID=reply.thread_id;

/* This query selects all of the flagged posts from the same university as the admin */
SELECT * FROM replies reply
	WHERE reply.flagged=1
    /*I may need to change this from the sys_user, I'm not sure yet how we will access the user's ID*/
	AND @sys_user.ID = (SELECT university_id FROM users
		WHERE users.ID= reply.owner_id);

/*This query selects all of the threads (aka questions) that a user has created*/
SELECT * FROM threads
WHERE threads.owner_id= @sys_user.ID;