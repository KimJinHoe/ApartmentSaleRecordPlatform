import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes",
  headers: {
    "Content-type": "application/json",
  },
});
