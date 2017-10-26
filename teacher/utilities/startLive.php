<?php
  session_start();
  //Check if user is logged in
  if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
    if($_SESSION['role'] != 2) {
      $_SESSION['error'] = true;
      $_SESSION['errorCode'] = "Not Permitted";
      header("Location: ../../login.php");
      die("oops");
    }
  } else {
    $_SESSION['error'] = true;
    $_SESSION['errorCode'] = "Session Expired";
    header("Location: ../../login.php?event=logout");
  }
  //TODO Grab all this from a file
  //Define sql database information
  $host="mysql.cs.iastate.edu";
  $port=3306;
  $socket="";
  $user="dbu309sab3";
  $password="SD0wFGqd";
  $dbname="db309sab3";
  //Connect to database
  $db = new mysqli($host, $user, $password, $dbname, $port, $socket) or die ('Could not connect to the database server'. mysqli_connect_error());
  //Print out host information
  //echo $db->host_info;

  $create = "CREATE TABLE liveQueue" . $_GET['class'] . " (
  ID int(11) NOT NULL AUTO_INCREMENT,
  username varchar(20) DEFAULT NULL,
  class_id int(11) NOT NULL,
  creation timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  txt varchar(400) DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY class_id (class_id),
  KEY username (username),
  CONSTRAINT liveQueue" . $_GET['class'] . "_ibfk_1 FOREIGN KEY (class_id) REFERENCES classes (ID),
  CONSTRAINT liveQueue" . $_GET['class'] . "_ibfk_2 FOREIGN KEY (username) REFERENCES users (username)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1";

  $result = $db->query($create) or die($db->error);
  header("Location: ../liveSession.php?class=" . $_GET['class']);


?>
