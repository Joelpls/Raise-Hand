<?php
  session_start();
  //Define paramiters and initalize connection
  $host = "127.0.0.1";
  $username = "root";
  $password = "raisehand";
  $database = "topics";
  $db = new mysqli($host, $username, $password, $database, 3306) or die('Error connecting to server');

  //Check for empty comment
  /*
  if("" == trim($_POST['username'])) {
    $username = 'Anonymous';
  }
  else {
    $username = $_POST['username'];

  }
  */

  $username = $_SESSION['username'];
  //$parentID = $_POST['parentID'];
  //Do not allow empty comments or parent id fields
  if(("" != trim($_POST['comment'])) && ("" != trim($_POST['parentID']))) {
    $comment =  $_POST['comment'];
    $parentID = $_POST['parentID'];
    putComment($parentID, $username, $comment, $db);
  }

  //If we ever get here, just go back to forum
  header("Location: index.php");

  //Function to send a new comment to the database
  function putComment($parentID, $owner, $text, $db) {
    $timestamp = date('Y-m-d H:i:s.u');
    $query = "INSERT INTO posts (id, parent, owner, points, endorsed, timestamp, text) VALUES (NULL, '$parentID', '$owner', '0', '0', '$timestamp', '$text')";
    //echo $query;
    $result = $db->query($query) or die($db->error);
    header("Location: connection.php");
  }
?>
