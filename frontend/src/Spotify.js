import React, { Component } from 'react'
import Autocomplete from "react-google-autocomplete"

const AUTH_ENDPOINT = "https://accounts.spotify.com/authorize";
const CLIENT_ID = "3f389487771f4a878c30c760dd9f813b";
const REDIRECT_URL = "http://localhost:8080";
const SCOPES = [
  "user-top-read",
  "user-read-currently-playing",
  "user-read-playback-state",
];

class Spotify extends Component {

  state = {
    items: [],
    code: '',
    city: ''
  }

  searchWeather = this.searchWeather.bind(this);

  getParamValues = (url) => {
    return url
      .slice(1)
      .split('&')
      .reduce((prev, curr) => {
        const [title, value] = curr.split('=');
        prev[title] = value;
        return prev;
      }, {});
  }

  searchWeather(){

    const { city } = this.state;


    const token = localStorage.getItem("code")
    console.log(token)

    console.log("--------------------------------------")
    console.log(city)






  }

  componentDidMount() {

    const {location } = this.props;

    const jsonparam = this.getParamValues(location.search);

    localStorage.setItem('code', jsonparam.code)


    //this.setState()

    this.getItems()
  }

  render() {
    return (

      <div style={{ flex: 1, display: 'flex', justifyContent: 'center', alignItems: 'center', flexDirection: 'row' }}>
        <a href={`https://accounts.spotify.com/authorize?client_id=3f389487771f4a878c30c760dd9f813b&response_type=code&redirect_uri=http://localhost:8080&scope=user-read-private%20user-read-email&state=34fFs29kd09`}>
          Login to Spotify
        </a>

        <br/><br/>

        <input
              type="text" 
              placeholder="Digite o nome da cidade" 
              value={ this.state.city } 
              onChange={ (e) => this.setState({ city: e.target.value }) } />
              
              <button onClick={()=>this.searchWeather()}>
                OK
              </button>

      </div>





    )
  }
}

export default Spotify