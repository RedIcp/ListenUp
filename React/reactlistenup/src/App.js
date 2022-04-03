import User from './Admin/Users.js';
import SignUp from './ThirdParty/SignUp.js';
import UpdateProfile from './Customer/UpdateProfile.js';

function App() {
  return (
      <div ClassName="App">
        <User/>
        <SignUp/>
        <UpdateProfile/>
      </div>
  )
}

export default App;