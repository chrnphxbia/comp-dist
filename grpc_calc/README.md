# Implementação: Calculadora gRPC com Python 

## Como executar este projeto?
No diretório `calc`, execute o seguinte comando:

`python -m grpc_tools.protoc -I. --python_out=. --grpc_python_out=. calc.proto`

Este comando gerará os arquivos `calc_pb2.py` e `calc_pb2_grpc.py`.

> Obs.: Para obter sucesso, é necessário ter instalado as ferramentas `grpc` e `grpc-tools`.

Por fim, em um terminal, utilize `python calc_server.py` para iniciar o servidor.

Logo em seguida, execute `python calc_client.py` para executar o cliente da calculadora.

## Verificando resultados
Caso a execução seja bem sucedida, a saída da aplicação será semelhante à imagem abaixo: