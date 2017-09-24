<?php
  session_start();
  //Check if user is logged in
  if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
    if($_SESSION['role'] != 2) {
      $_SESSION['error'] = true;
      $_SESSION['errorCode'] = "Not a teacher";
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
?>

<!-- This is the format we will use for the pages on the website. CSS to be added as
    time goes on and I continue to learn more. -->
<html lang="en">
  <head>
    <link rel="stylesheet" href="../css/home.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    <!-- The top banner of the webpage -->
    <div class="top">
        <font size="-5"><a class="logout" href="login.php?event=logout">Logout</a></font>
        <center>
          <?php
            echo "<h1>Welcome, " . $_SESSION['name'] . "</h1>";
          ?>
        </center>
    </div>
  </head>



  <!-- Main content of the webpage "do the wave" -->
<body>
  <div class="main">
    <br>
    <div class="container-fluid">
      <?php
        //Get the classes that the teacher is a teacher of
        $query = "SELECT * FROM classes WHERE teacher_id = 6";
        $result = $db->query($query) or die($db->error);
        while ($class = $result->fetch_assoc()) {
          echo '<div class="row">
                  <div class="col-md-6">
                    <div class="home">
                      <center>
                        <br>
                        <button class="button" onclick="window.location=\'pages.php?class=' . $class['ID'] . '\';">' . $class['class_name'] . '</button>
                        <p>
                          Class description here.
                        </p>
                      </center>
                    </div>
                  </div>';
          if($class = $result->fetch_assoc()) {
            echo '<div class="col-md-6">
                      <div class="home">
                        <center>
                          <br>
                          <button class="button" onclick="window.location=\'pages.php?class=' . $class['ID'] . '\';">' . $class['class_name'] . '</button>
                          <p>
                            Class description here.
                          </p>
                        </center>
                      </div>
                    </div>
                  </div>
                    <br>';
          }
        }

       ?>
    </div>
  </div>
</body>
</html>
