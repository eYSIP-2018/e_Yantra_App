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
//Route for Maps API
Route::get('youtube','Eyantra\ResourceController@youtube');
//Route for Youtube API
Route::get('projects','Eyantra\ResourceController@project');
//Route for Projects API
Route::get('tutorials','Eyantra\ResourceController@tut');
//Route for Tutorials API

Route::post('sign','Eyantra\SignController@store');
//Route for Google or Facebook Sign in 
Route::post('post','Eyantra\PostController@store');
//Route for posting in web forum
Route::get('showpost','Eyantra\PostController@show');
//Route for showing posts
Route::post('image','Eyantra\PostController@image');

Route::post('comment','Eyantra\CommentController@store');
//Route for commenting on photos
Route::post('commentshow','Eyantra\CommentController@show');
//Route for showing comments of a particular post
Route::post('commentcount','Eyantra\CommentController@count');

Route::post('news','Eyantra\NewsController@store');
//Route for news feed
Route::get('newshow','Eyantra\NewsController@show');
//Route for recieving new
Route::post('newscom','Eyantra\NewscomController@store');
//Route for comments on news
Route::post('newscomshow','Eyantra\NewscomController@show');
//Route for comments on the news feed
Route::post('newscount','Eyantra\NewscomController@count');
//Route for comment count
Route::post('profile','Eyantra\ProfileController@store');
//Route for My Page
Route::post('profshow','Eyantra\ProfileController@show');
Route::post('calender','Eyantra\CalenderController@show');