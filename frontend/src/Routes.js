import React from 'react';
import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom';
import Spotify   from './Spotify'

function Routes(props) {
  return(
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={ Spotify } />
      </Switch>
    </BrowserRouter>
  );
}

export default Routes