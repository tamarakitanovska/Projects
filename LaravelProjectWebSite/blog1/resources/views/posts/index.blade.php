@extends('main')
@section('title', '| AllPosts')
@section('content')
    <h1>All Posts: </h1>
    <a href="{{route('posts.create')}}">Create New Post</a>
    <hr>
    <table>
        <thead>
        <th style="width: 50px;">#</th>
        <th style="width: 50px;">Title</th>
        <th style="width: 200px;">Body</th>
        <th style="width: 200px;">Created At</th>
        <th></th>
        </thead>
        <tbody>
        @foreach($posts as $post)
            <div class="post">
            <tr>
                <th style="width: 50px;">{{$post->id}}  </th>
                <td style="width: 50px;">{{$post->title}}  </td>
                <td style="width: 200px;">{{substr($post->name,0,50)}}{{strlen($post->name)>50 ? "..." : ""}}</td>
                <td style="width: 200px;">{{date('M j, Y',strtotime($post->created_at))}}  </td>
                <td style="width: 200px;"><a href="{{route('posts.show', $post->id)}}" class="btn btn-default btn-md">View</a> <a href="{{route('posts.edit', $post->id)}}" class="btn btn-default btn-md">Edit</a></td>
            </tr>
            </div>
            @endforeach
        </tbody>
    </table>
    @endsection