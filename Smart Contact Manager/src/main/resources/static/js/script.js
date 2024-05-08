console.log("script loaded");

//change theme work
let currentTheme = getTheme();
//initial -->

document.addEventListener('DOMContentLoaded',() =>{ changeTheme(currentTheme); });


function changeTheme() {
	//set to webpage
	changePageTheme(currentTheme, currentTheme);
	//set the listener to change theme button
	const changeThemeButton = document.querySelector('#theme_change_button');
	//get the current theme
	changeThemeButton.addEventListener("click", (event) => {
		let oldTheme = currentTheme;
		console.log("change theme botton click");

		if (currentTheme === "dark") {
			//th light theme
			currentTheme = "light";
		}
		else {
			currentTheme = "dark";
		}

		changePageTheme(currentTheme, oldTheme);
	});
}

//set theme to local storage
function setTheme(theme) {
	localStorage.setItem("theme", theme);
}

//get theme from local storage
function getTheme() {
	let theme = localStorage.getItem("theme");
	if (theme)
		return theme
	else return "light";
}

//change current page theme
function changePageTheme(theme, oldTheme) {
	//update on local storage
	setTheme(currentTheme);
	//remove the current theme
	document.querySelector('html').classList.remove(oldTheme);
	//set the current theme
	document.querySelector('html').classList.add(theme);

	//change the text of button
	document.querySelector('#theme_change_button').querySelector("span").textContent = theme == "light" ? "Dark" : "Light";

}
//end of change theme work