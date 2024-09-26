# NOME: Pedro Henrique Araujo Farias
# RA: 10265432

import grpc
from concurrent import futures
import calc_pb2
import calc_pb2_grpc

def gcd(a, b):
    while b:
        a, b = b, a % b
    return a

def simplify_fraction(numerator, denominator):
    divisor = gcd(numerator, denominator)
    return numerator // divisor, denominator // divisor

class CalculatorServicer(calc_pb2_grpc.CalculatorServicer):

    def AddFrac(self, request, context):
        num = request.fraction1.numerator * request.fraction2.denominator + request.fraction2.numerator * request.fraction1.denominator
        den = request.fraction1.denominator * request.fraction2.denominator
        num, den = simplify_fraction(num, den)
        return calc_pb2.FracRes(result=calc_pb2.Frac(numerator=num, denominator=den))

    def SubFrac(self, request, context):
        num = request.fraction1.numerator * request.fraction2.denominator - request.fraction2.numerator * request.fraction1.denominator
        den = request.fraction1.denominator * request.fraction2.denominator
        num, den = simplify_fraction(num, den)
        return calc_pb2.FracRes(result=calc_pb2.Frac(numerator=num, denominator=den))
    
    def MultFrac(self, request, context):
        num = request.fraction1.numerator * request.fraction2.numerator
        den = request.fraction1.denominator * request.fraction2.denominator
        num, den = simplify_fraction(num, den)
        return calc_pb2.FracRes(result=calc_pb2.Frac(numerator=num, denominator=den))

    def DivFrac(self, request, context):
        num = request.fraction1.numerator * request.fraction2.denominator
        den = request.fraction1.denominator * request.fraction2.numerator
        num, den = simplify_fraction(num, den)
        return calc_pb2.FracRes(result=calc_pb2.Frac(numerator=num, denominator=den))

    def MaxFrac(self, request, context):
        if request.fraction1.numerator * request.fraction2.denominator >= request.fraction2.numerator * request.fraction1.denominator:
            result = request.fraction1
        else:
            result = request.fraction2
        return calc_pb2.FracRes(result=result)

    def MinFrac(self, request, context):
        if request.fraction1.numerator * request.fraction2.denominator <= request.fraction2.numerator * request.fraction1.denominator:
            result = request.fraction1
        else:
            result = request.fraction2
        return calc_pb2.FracRes(result=result)

def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    calc_pb2_grpc.add_CalculatorServicer_to_server(CalculatorServicer(), server)
    server.add_insecure_port('[::]:50054')
    server.start()
    server.wait_for_termination()

if __name__ == '__main__':
    serve()