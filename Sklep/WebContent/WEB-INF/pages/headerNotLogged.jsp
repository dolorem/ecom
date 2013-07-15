<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">Project name</a>
          <div class="nav-collapse collapse">
          	<ul class="nav pull-right">
          	<li>
	            <form action="/j_spring_security_check" method="post" class="navbar-form pull-right">
	              	<input class="span2" type="text" id="j_username" name="j_username" placeholder="Nazwa użytkownika">
	              	<input class="span2" type="password" name="j_password" id="j_password" placeholder="Hasło">
	              	<button type="submit" class="btn">Zaloguj</button>
	            </form>
            </li>
            <li><a href="/auth/register.htm">Zarejestruj się</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>