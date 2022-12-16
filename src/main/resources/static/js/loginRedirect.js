sessionStorage.setItem('jwtToken', token);
setTimeout(() => {window.location.assign('https://webapp-financeira.herokuapp.com/home'); }, 500);
