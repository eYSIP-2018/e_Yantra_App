<?php

namespace App\Http\Controllers\Eyantra;
use App\Newscom;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;

class NewscomController extends Controller
{
    public function store(Request $request)
	{
    $input= new Newscom;
		$input->username=$request->username;
		$input->Comments=$request->Comments;
		$input->Fid=$request->Fid;
		$input->photo=$request->photo;
		$input->save();
		echo "Saved";
	}
	public function show(Request $request)
	{
		$input=$request->Fid;
		$data = Newscom::where('Fid',"$input")
		->orderBy('ID','DESC')->get();
		echo $data;
		
	}
	public function count(Request $request)
	{
		$input=$request->Fid;
		$count = new \stdClass();
		$count->Response =Newscom::where('Fid',"$input")
		->count();
		echo json_encode($count);
	}
}
