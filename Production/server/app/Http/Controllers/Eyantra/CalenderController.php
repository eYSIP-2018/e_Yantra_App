<?php

namespace App\Http\Controllers\Eyantra;
use App\Calender;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;

class CalenderController extends Controller
{
	public function show(Request $request)
	{
    $input=$request->date;
		
		$data = Calender::where('Date',"$input")->get();
		echo json_encode($data);

	}
}
