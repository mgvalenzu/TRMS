// var employee = null;

async function loginEmployee() {
    // Extract info from page
    const username = document.getElementById("userInput").value;
    const password = document.getElementById("passInput").value;
    const error = document.getElementById("loginError")

    try {
        const url = `http://localhost:7000/employees/${username}/employees/${password}`;
        const httpResponse = await fetch(url);
        const body = await httpResponse.json();
        console.log(body);
        // employee = body;



        //Determine if player is benco/supervisor to anyone
        if (body["isBenco"]){
            console.log("Ok this person is benco");
        }
        // Place employee object as string in storage to retreive on next page
        localStorage.setItem('currEmployee', JSON.stringify(body));
        
        //successfully logged in now time to move to home page for employee
        error.innerHTML = "Good job!"
        location.assign("./home.html");
 
    } catch (err){
        error.innerHTML = "The username or password entered are incorrect.\n\nPlease try again."
    }
}
