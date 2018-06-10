@extends('main')
@section('title', ' | Homepage')
@section('content')
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="jumbotron">
                    <h1>Welcome to my Blog!</h1>
                    <p class="lead">Thank you for visiting. This is my test web site built with Laravel. Please read my popular post.</p>
                    <p><a class="btn btn-primary btn-lg" href="#" role="button">Popular Post</a></p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-8">
                <div class="post">
                    <h3>Post Title</h3>
                    <p>
                        A simple shell for an h1 to appropriately space out and segment sections of content on a page. It can utilize the h1's default small element, as well as most other components (with additional styles).
                    </p>
                    <a href="#"><button class="btn btn-primary">Read more</button></a>
                </div>
                <hr>
                <div class="post">
                    <h3>Post Title</h3>
                    <p>
                        A simple shell for an h1 to appropriately space out and segment sections of content on a page. It can utilize the h1's default small element, as well as most other components (with additional styles).
                    </p>
                    <a href="#"><button class="btn btn-primary">Read more</button></a>
                </div>
                <hr>
                <div class="post">
                    <h3>Post Title</h3>
                    <p>
                        A simple shell for an h1 to appropriately space out and segment sections of content on a page. It can utilize the h1's default small element, as well as most other components (with additional styles).
                    </p>
                    <a href="#"><button class="btn btn-primary">Read more</button></a>
                </div>
                <hr>
                <div class="post">
                    <h3>Post Title</h3>
                    <p>
                        A simple shell for an h1 to appropriately space out and segment sections of content on a page. It can utilize the h1's default small element, as well as most other components (with additional styles).
                    </p>
                    <a href="#"><button class="btn btn-primary">Read more</button></a>
                </div>
            </div>
            <div class="col-md-3 col-md-offset-1">
                <h2>Side Bar</h2>
            </div>
        </div>
    </div>
@endsection