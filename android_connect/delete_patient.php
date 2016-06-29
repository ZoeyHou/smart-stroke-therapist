<?php
    // array for JSON response
    $response= array();
    // check for required fields
    if(isset($_POST['patient_id'])) {
        $patient_id= intval($_POST['patient_id']);
        // include db connect class
        require_once__DIR__ . '/db_connect.php';
        // connecting to db
        $db= new DB_CONNECT();
        // mysql delete row with matched patient_id
        $result= mysql_query("DELETE FROM patient WHERE patient_id = $patient_id");
        // check if row deleted or not
        if(mysql_affected_rows() > 0) {
            // successfully updated
            $response["success"] = 1;
            $response["message"] = "Product successfully deleted";
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
        // required field is missing 
        $response["success"] = 0; 
        $response["message"] = "Required field(s) is missing"; 
        // echoing JSON response 
        echo json_encode($response); 
    } 
    ?>