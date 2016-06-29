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
        // get test results from table
        $result= mysql_query("SELECT doctor_advice FROM settings WHERE patient_id = $patient_id");
        if(!empty($result)) {
            // check for empty result
            if(mysql_num_rows($result) > 0) {
                $result= mysql_fetch_array($result);
                $patient= array();
                $patient["patient_id"] = strval($result["patient_id"]);
                $patient["doctor_advice"] = $result["doctor_advice"];
                // success
                $response["success"] = 1;
                // user node
                $response["patient"] = array();
                array_push($response["test_result"], $test_result);
                // echoing JSON response
                echo json_encode($response);
            } else{
                // no results found
                $response["success"] = 0;
                $response["message"] = "No results found";
                // echo no users JSON 
                echo json_encode($response);
            } 
        } else{ 
            // no results found
            $response["success"] = 0; 
            $response["message"] = "No results found";
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