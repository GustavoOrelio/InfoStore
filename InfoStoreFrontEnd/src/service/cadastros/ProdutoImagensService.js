import { ServiceBase } from './ServiceBase';
import Axios from "axios";

export class ProdutoImagensService extends ServiceBase {

    constructor(){
        super("produtoImagens");
    }

    uploadImagens(obj){
        const formData = new FormData();
        formData.append('idProduto', obj.idProduto);
        formData.append('file', obj.file);
        const config ={
            headers :{
                'content-type':'multipart/form-data'
            }
        }
        return Axios.post(this.url, formData, config);
    }

    buscarPorProduto(idProduto){
        return Axios.get(this.url+"produto/"+idProduto);
    }

}
