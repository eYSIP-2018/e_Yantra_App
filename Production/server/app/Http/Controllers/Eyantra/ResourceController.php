<?php

namespace App\Http\Controllers\Eyantra;
use App\Map;
use App\Youtube;
use App\Project;
use App\Tutorial;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;

class ResourceController extends Controller
{
    public function latlong()
	{
		$mapdata=Map::all();
		echo json_encode($mapdata);
	}
	public function youtube()
	{
		$video=Youtube::all();
		echo json_encode($video);
	}
	public function project()
	{
		$proj=Project::all();
		echo json_encode($proj);
	}
	public function tut()
	{
		$tuto=Tutorial::all();
		echo json_encode($tuto);
	}
}
