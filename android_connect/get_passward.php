<?php
    // array for JSON response
    $response= array();
    // include db connect class
    require_once__DIR__ . '/db_connect.php';
    // connecting to db
    $db= new DB_CONNECT();
    // check for post data
    if(isset($_GET["patient_id"])) {
        $patient_id= intval($_GET['patient_id']);
        // get a patient from table
        $result= mysql_query("SELECT patient_pwd FROM patient WHERE patient_id = $patient_id");
        if(!empty($result)) {
            // check for empty result
            if(mysql_num_rows($result) > 0) {
                $result= mysql_fetch_array($result);
                $patient= array();
                $patient["patient_pwd"] = $result["patient_pwd"];
                // success
                $response["success"] = 1;
                // user node
                $response["patient"] = array();
                array_push($response["patient"], $patient);
                // echoing JSON response
                echo json_encode($response);
            } else{
                // no patient found
                $response["success"] = 0;
                $response["message"] = "No patient found";
                // echo no users JSON 
                echo json_encode($response);
            } 
        } else{ 
            // no patient found
            $response["success"] = 0; 
            $response["message"] = "No patient found"; 
            // echo no users JSON 
            echo json_encode($response);
        }
        // or it could be a relative
        $result = mysql_query("SELECT relative_pwd FROM relative WHERE relative_id = $patient_id");
        if(!empty($result)) {
            // check for empty result
            if(mysql_num_rows($result) > 0) {
                $result= mysql_fetch_array($result);
                $relative= array();
                $relative["relative_pwd"] = $result["relative_pwd"];
                // success
                $response["success"] = 1;
                // user node
                $response["relative"] = array();
                array_push($response["relative"], $relative);
                // echoing JSON response
                echo json_encode($response);
            } else{
                // no relative found
                $response["success"] = 0;
                $response["message"] = "No relative found";
                // echo no users JSON
                echo json_encode($response);
            }
        } else{
            // no relative found
            $response["success"] = 0;
            $response["message"] = "No relative found";
            // echo no users JSON
            echo json_encode($response);
        }
    } else{
        // required field is missing
        $response["success"] = 0; 
        $response["message"] = "Required field(s) is missing"; 
        // echoing JSON response 
        echo json_encode($response); 
    } 
    ?>