import React from 'react';
import { withCookies } from 'react-cookie';

import Routes from './Routes';

function App() {
  return (
    <>
      <Routes />
    </>
  );
}

export default withCookies(App);