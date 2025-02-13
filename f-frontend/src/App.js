import {BrowserRouter as Router, Route, Routes} from "react-router";
import ListPage from "./pages/ListPage";
import CreatePage from "./pages/CreatePage";

function App() {
  return (
    <div className={"flex flex-auto w-full"}>
        <Routes>
          <Route path={'/'} element={<ListPage/>}/>
          <Route path={'/create'} element={<CreatePage />}/>
          <Route path={'/create/:id'} element={<CreatePage />} />
        </Routes>
    </div>
  );
}

export default App;
