<?php

namespace App\Http\Controllers\Eyantra;
use App\News;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;

class NewsController extends Controller
{
	public function store(Request $request)
	{
    $input= new News;
		$input->username=$request->username;
		$input->desc=$request->desc;
		//$input->title=$request->title;
		$input->save();
		echo "Saved";
	}
	public function show()
	{
		$data=News::orderBy('ID','DESC')->get();
		echo json_encode($data);

	}
	
}
