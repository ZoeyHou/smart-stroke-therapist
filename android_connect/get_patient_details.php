<?php
    // array for JSON response
    $response= array();
    // include db connect class
    require_once__DIR__ . '/db_connect.php';
    // connecting to db
    $db= newDB_CONNECT();
    // check for post data
    if(isset($_GET["patient_id"])) {
        $patient_id= $_GET['patient_id'];
        // get a patient from table
        $result= mysql_query("SELECT *FROM patient WHERE patient_id = $patient_id");
        if(!empty($result)) {
            // check for empty result
            if(mysql_num_rows($result) > 0) {
                $result= mysql_fetch_array($result);
                $patient= array();
                $patient["patient_id"] = $result["patient_id"];
                $patient["patient_name"] = $result["patient_name"];
                $patient["gender"] = $result["gender"];
                $patient["age"] = $result["age"];
                $patient["height"] = $result["height"];
                $patient["weight"] = $result["weight"];
                $patient["leg_length"] = $result["leg_length"];
                // success
                $response["success"] = 1;
                // user node
                $response["patient"] = array();
                array_push($response["patient"], $patient);
                // echoing JSON response
                echojson_encode($response);
            } else{
                // no patient found
                $response["success"] = 0;
                $response["message"] = "No patient found";
                // echo no users JSON 
                echojson_encode($response); 
            } 
        } else{ 
            // no patient found
            $response["success"] = 0; 
            $response["message"] = "No patient found"; 
            // echo no users JSON 
            echojson_encode($response); 
        } 
    } else{ 
        // required field is missing 
        $response["success"] = 0; 
        $response["message"] = "Required field(s) is missing"; 
        // echoing JSON response 
        echojson_encode($response); 
    } 
    ?>