<?php
    //require_once 'sql.php';
	$host="mysql.cs.iastate.edu";
	$port=3306;
	$socket="";
	$user="dbu309sab3";
	$password="SD0wFGqd";
	$dbname="db309sab3";
	//Connect to database
	$db = new mysqli($host, $user, $password, $dbname, $port, $socket) or die ('Could not connect to the database server' . mysqli_connect_error());
	$question_id=$_GET['questionId'];
	$stmt = "SELECT * FROM replies WHERE thread_id='$question_id' AND parent=0";
	$stmt = $db->query($stmt) or die($db->error);
	die("Done");
?>
