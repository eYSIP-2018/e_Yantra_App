<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});
Route::get('maps','Eyantra\ResourceController@latlong');
Route::get('youtube','Eyantra\ResourceController@youtube');
Route::get('projects','Eyantra\ResourceController@project');
Route::get('tutorials','Eyantra\ResourceController@tut');

Route::post('sign','Eyantra\SignController@store');
Route::post('post','Eyantra\PostController@store');
Route::get('showpost','Eyantra\PostController@show');
Route::post('image','Eyantra\PostController@image');
Route::post('comment','Eyantra\CommentController@store');
Route::post('commentshow','Eyantra\CommentController@show');