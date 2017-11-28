<?php
include '../../utilities/database.php';

  /*
    This file contains functions that help build class statistics.
  */

  //Get the db Referance
  $db = getDB();
  $classID = $_GET['class'];
  /// Gets a list of replies posted in a class.
  /// @param $db the database to query.
  /// @param $classID.
  /// @return list of reply arrays that contain replies

  //Make a query that gets all the threads of a class
  $query = "SELECT  ID, topic_id, owner_id, title, description, creation, user_name
            FROM  threads";
  //Due to format of db we have to get the topics of a class first
  $topics = getTopics($db, $classID);
  //For every topic, add it to the possible topics we want thread from
  $firstT = 1;
  while($thisT = $topics->fetch_assoc()) {
    if($firstT) {
      $query = $query . " WHERE topic_id = " . $thisT['ID'];
      $firstT = 0;
    }
    else {
      $query = $query . " or topic_id = " . $thisT['ID'];
    }
  }
  //Finally, get recent threads from this class
  $result = $db->query($query) or die($db->error);

  //Now lets get all the replies in each of these threads
  $newQuery = "SELECT user_name, creation FROM replies WHERE thread_id = -1";

  //For every thread in this class
  while($thread = $result->fetch_assoc()) {
    $newQuery = $newQuery . " OR thread_id = " . $thread['ID'];
  }

  //Now that we have this new query built we can query for all the replies.
  $result = $db->query($newQuery) or die($db->error);

  $data = array();

  while($reply = $result->fetch_assoc()) {
    $uname = $reply['user_name'];
    if($data[$uname]) {
      $data[$uname] = $data[$uname] + 1;
    }
    else {
      $data[$uname] = 1;
    }
  }

  echo json_encode($data);


?>
