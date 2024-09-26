# NOME: Pedro Henrique Araujo Farias
# RA: 10265432

import grpc
import calc_pb2
import calc_pb2_grpc

def create_fraction(numerator, denominator):
    return calc_pb2.Frac(numerator=numerator, denominator=denominator)

def run():
    with grpc.insecure_channel('localhost:50054') as channel:
        stub = calc_pb2_grpc.CalculatorStub(channel)

        frac1 = create_fraction(7, 7)
        frac2 = create_fraction(7, 49)
        print(f"F1: {frac1.numerator}/{frac1.denominator}")
        print(f"F2: {frac2.numerator}/{frac2.denominator}")

        print("\n************** ADDITION ************** ")
        response = stub.AddFrac(calc_pb2.FracReq(fraction1=frac1, fraction2=frac2))
        print(f"Result: {response.result.numerator}/{response.result.denominator}")

        print("\n************ SUBTRACTION ************* ")
        response = stub.SubFrac(calc_pb2.FracReq(fraction1=frac1, fraction2=frac2))
        print(f"Result: {response.result.numerator}/{response.result.denominator}")

        print("\n********** MULTIPLICATION ************ ")
        response = stub.MultFrac(calc_pb2.FracReq(fraction1=frac1, fraction2=frac2))
        print(f"Result: {response.result.numerator}/{response.result.denominator}")

        print("\n************** DIVISION ************* ")
        response = stub.DivFrac(calc_pb2.FracReq(fraction1=frac1, fraction2=frac2))
        print(f"Result: {response.result.numerator}/{response.result.denominator}")
        
        print("\n**************** MAX **************** ")
        response = stub.MaxFrac(calc_pb2.FracReq(fraction1=frac1, fraction2=frac2))
        print(f"Result: {response.result.numerator}/{response.result.denominator}")
        
        print("\n**************** MIN **************** ")
        response = stub.MinFrac(calc_pb2.FracReq(fraction1=frac1, fraction2=frac2))
        print(f"Result: {response.result.numerator}/{response.result.denominator}")

if __name__ == '__main__':
	run()