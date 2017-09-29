<?php
  //Start a session
  session_start();
  //Define paramiters and initalize connection
  $host="mysql.cs.iastate.edu";
	$port=3306;
	$socket="";
	$user="dbu309sab3";
	$password="SD0wFGqd";
	$dbname="db309sab3";
  $db = new mysqli($host, $user, $password, $dbname, $port, $socket) or die ('Could not connect to the database server' . mysqli_connect_error());
  //Print out host information
  echo $db->host_info;

  //Check and make sure they entered a username
  if("" == trim($_POST['username'])) {
    $_SESSION['error'] = true;
    $_SESSION['errorCode'] = "No Username";
    header("Location: ../login.php");
    die("No username.");
  }
  //Check and make sure they entered a password
  else if("" == trim($_POST['password'])) {
    $_SESSION['error'] = true;
    $_SESSION['errorCode'] = "No Password";
    header("Location: ../login.php");
    die("No password.");
  }
  //Injection? Maybe, maybe not. Maybe screw you.
  else if (strpos($comment, ';')) {
    $_SESSION['error'] = true;
    $_SESSION['errorCode'] = "No thanks.";
    header("Location: ../login.php");
    die("Injection attempt");
  }
  //If both fields are populated correctly then execute login function
  else {
    //Get post variables and send to function
    $password = $_POST['password'];
    $username = $_POST['username'];
    signIn($username, $password, $db);
  }

  //Function to enter new user into database
  function signIn($username, $password, $db) {
    //Create sql command
    $insert = "SELECT * FROM users WHERE username = '$username' ";
    //Excecute
    $result = $db->query($insert) or die($db->error);
    //Get the data of the username they specified
    $pass = $result->fetch_assoc();
    //Get ID
    $id = $pass['ID'];
    //Get name
    $name = $pass['first_name'];
    //Check for admin/teacher privilages
    $role = $pass["role_id"];
    //Get hashed password from database
    $passs = $pass["pass"];
    //If the passwords match
    if(password_verify($password, $passs)) {
      if($pass['reset']) {
        $_SESSION['loggedin'] = true;
        $_SESSION['username'] = $username;
        $_SESSION['id'] = $id;
        $_SESSION['name'] = $name;
        $_SESSION['error']  = false;
        $_SESSION['role'] = $role;
        //User has a password Reset
        header("Location: ../resetPassword.php");
        die("Go reset the password");
      }
      //Set logged in variables
      echo "success";
      $_SESSION['loggedin'] = true;
      $_SESSION['username'] = $username;
      $_SESSION['id'] = $id;
      $_SESSION['name'] = $name;
      $_SESSION['error']  = false;
      $_SESSION['role'] = $role;
      if($role == 1) {
        header("Location: ../admin/home.php");
        die("Going to admin panel");
      }
      else if($role == 2) {
        header("Location: ../teacher/home.php");
        die("Going to admin panel");
      }
      else if($role == 4) {
        header("Location: ../student/home.php");
        die("Going to admin panel");
      }
      //Send user to their homepage
	    echo “success”;
      header("Location: ../login.php");



    }
    else {
        echo "fail";
      //Set session varibales to notify user
      $_SESSION['loggedin'] = false;
      $_SESSION['error'] = true;
      $_SESSION['errorCode'] = "Incorrect Password";
      //Send user back to login page if passwords didnt match
      header("Location: ../login.php");
    }
  }
?>
