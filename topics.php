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
	
	$classId = $_GET['classId'];
    $question = "SELECT * FROM topics WHERE class_id='$classId'";
    //Excecute
    $result = $db->query($question) or die($db->error);
	die($result);
?>