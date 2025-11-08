package com.wetech.demo.web3j.contracts.lctoken;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.14.0.
 */
@SuppressWarnings("rawtypes")
public class LCToken extends Contract {
    public static final String BINARY = "608060405234801561000f575f5ffd5b506040805180820190915260078152662621aa37b5b2b760c91b60208201525f9061003a9082610111565b506040805180820190915260038152621310d560ea1b60208201526001906100629082610111565b506002805460ff191660121790555f6003556101cb565b634e487b7160e01b5f52604160045260245ffd5b600181811c908216806100a157607f821691505b6020821081036100bf57634e487b7160e01b5f52602260045260245ffd5b50919050565b601f82111561010c57805f5260205f20601f840160051c810160208510156100ea5750805b601f840160051c820191505b81811015610109575f81556001016100f6565b50505b505050565b81516001600160401b0381111561012a5761012a610079565b61013e81610138845461008d565b846100c5565b6020601f821160018114610170575f83156101595750848201515b5f19600385901b1c1916600184901b178455610109565b5f84815260208120601f198516915b8281101561019f578785015182556020948501946001909201910161017f565b50848210156101bc57868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b610c69806101d85f395ff3fe608060405234801561000f575f5ffd5b50600436106100b1575f3560e01c806342966c681161006e57806342966c681461014357806370a082311461015657806379cc67901461017e57806395d89b4114610191578063a9059cbb14610199578063dd62ed3e146101ac575f5ffd5b806306fdde03146100b5578063095ea7b3146100d357806318160ddd146100f657806323b872dd14610108578063313ce5671461011b57806340c10f1914610130575b5f5ffd5b6100bd6101e4565b6040516100ca9190610a50565b60405180910390f35b6100e66100e1366004610aa0565b610273565b60405190151581526020016100ca565b6003545b6040519081526020016100ca565b6100e6610116366004610ac8565b610334565b60025460405160ff90911681526020016100ca565b6100e661013e366004610aa0565b610544565b6100e6610151366004610b02565b610626565b6100fa610164366004610b19565b6001600160a01b03165f9081526004602052604090205490565b6100e661018c366004610aa0565b61073a565b6100bd61094c565b6100e66101a7366004610aa0565b61095b565b6100fa6101ba366004610b39565b6001600160a01b039182165f90815260056020908152604080832093909416825291909152205490565b60605f80546101f290610b6a565b80601f016020809104026020016040519081016040528092919081815260200182805461021e90610b6a565b80156102695780601f1061024057610100808354040283529160200191610269565b820191905f5260205f20905b81548152906001019060200180831161024c57829003601f168201915b5050505050905090565b5f6001600160a01b0383166102cf5760405162461bcd60e51b815260206004820152601760248201527f496e76616c6964207370656e646572206164647265737300000000000000000060448201526064015b60405180910390fd5b335f8181526005602090815260408083206001600160a01b03881680855290835292819020869055518581529192917f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92591015b60405180910390a35060015b92915050565b5f6001600160a01b0384166103845760405162461bcd60e51b8152602060048201526016602482015275496e76616c69642073656e646572206164647265737360501b60448201526064016102c6565b6001600160a01b0383166103aa5760405162461bcd60e51b81526004016102c690610ba2565b6001600160a01b0384165f908152600460205260409020548211156104085760405162461bcd60e51b8152602060048201526014602482015273496e73756666696369656e742062616c616e636560601b60448201526064016102c6565b6001600160a01b0384165f90815260056020908152604080832033845290915290205482111561046f5760405162461bcd60e51b8152602060048201526012602482015271105b1b1bddd85b98d948195e18d95959195960721b60448201526064016102c6565b6001600160a01b0384165f9081526004602052604081208054849290610496908490610bed565b90915550506001600160a01b0383165f90815260046020526040812080548492906104c2908490610c00565b90915550506001600160a01b0384165f908152600560209081526040808320338452909152812080548492906104f9908490610bed565b92505081905550826001600160a01b0316846001600160a01b03165f516020610c145f395f51905f528460405161053291815260200190565b60405180910390a35060019392505050565b5f6001600160a01b03831661056b5760405162461bcd60e51b81526004016102c690610ba2565b5f82116105ba5760405162461bcd60e51b815260206004820152601b60248201527f4d696e742076616c7565206d75737420626520706f736974697665000000000060448201526064016102c6565b8160035f8282546105cb9190610c00565b90915550506001600160a01b0383165f90815260046020526040812080548492906105f7908490610c00565b90915550506040518281526001600160a01b038416905f905f516020610c145f395f51905f5290602001610322565b335f908152600460205260408120548211156106845760405162461bcd60e51b815260206004820152601c60248201527f496e73756666696369656e742062616c616e636520746f206275726e0000000060448201526064016102c6565b5f82116106d35760405162461bcd60e51b815260206004820152601b60248201527f4275726e2076616c7565206d75737420626520706f736974697665000000000060448201526064016102c6565b335f90815260046020526040812080548492906106f1908490610bed565b925050819055508160035f8282546107099190610bed565b90915550506040518281525f9033905f516020610c145f395f51905f529060200160405180910390a3506001919050565b5f6001600160a01b03831661078a5760405162461bcd60e51b8152602060048201526016602482015275496e76616c69642073656e646572206164647265737360501b60448201526064016102c6565b6001600160a01b0383165f908152600460205260409020548211156107f15760405162461bcd60e51b815260206004820152601c60248201527f496e73756666696369656e742062616c616e636520746f206275726e0000000060448201526064016102c6565b6001600160a01b0383165f9081526005602090815260408083203384529091529020548211156108585760405162461bcd60e51b8152602060048201526012602482015271105b1b1bddd85b98d948195e18d95959195960721b60448201526064016102c6565b5f82116108a75760405162461bcd60e51b815260206004820152601b60248201527f4275726e2076616c7565206d75737420626520706f736974697665000000000060448201526064016102c6565b6001600160a01b0383165f90815260046020526040812080548492906108ce908490610bed565b90915550506001600160a01b0383165f90815260056020908152604080832033845290915281208054849290610905908490610bed565b925050819055508160035f82825461091d9190610bed565b90915550506040518281525f906001600160a01b038516905f516020610c145f395f51905f5290602001610322565b6060600180546101f290610b6a565b5f6001600160a01b0383166109825760405162461bcd60e51b81526004016102c690610ba2565b335f908152600460205260409020548211156109d75760405162461bcd60e51b8152602060048201526014602482015273496e73756666696369656e742062616c616e636560601b60448201526064016102c6565b335f90815260046020526040812080548492906109f5908490610bed565b90915550506001600160a01b0383165f9081526004602052604081208054849290610a21908490610c00565b90915550506040518281526001600160a01b0384169033905f516020610c145f395f51905f5290602001610322565b602081525f82518060208401528060208501604085015e5f604082850101526040601f19601f83011684010191505092915050565b80356001600160a01b0381168114610a9b575f5ffd5b919050565b5f5f60408385031215610ab1575f5ffd5b610aba83610a85565b946020939093013593505050565b5f5f5f60608486031215610ada575f5ffd5b610ae384610a85565b9250610af160208501610a85565b929592945050506040919091013590565b5f60208284031215610b12575f5ffd5b5035919050565b5f60208284031215610b29575f5ffd5b610b3282610a85565b9392505050565b5f5f60408385031215610b4a575f5ffd5b610b5383610a85565b9150610b6160208401610a85565b90509250929050565b600181811c90821680610b7e57607f821691505b602082108103610b9c57634e487b7160e01b5f52602260045260245ffd5b50919050565b60208082526019908201527f496e76616c696420726563697069656e74206164647265737300000000000000604082015260600190565b634e487b7160e01b5f52601160045260245ffd5b8181038181111561032e5761032e610bd9565b8082018082111561032e5761032e610bd956feddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3efa2646970667358221220e93901f6e25d385e447ec077bc7d580bc2b96d646ef3fceac1c43abb0f93eb4764736f6c634300081d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_BURNFROM = "burnFrom";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected LCToken(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected LCToken(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected LCToken(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected LCToken(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ApprovalEventResponse> getApprovalEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ApprovalEventResponse getApprovalEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(APPROVAL_EVENT, log);
        ApprovalEventResponse typedResponse = new ApprovalEventResponse();
        typedResponse.log = log;
        typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse._spender = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getApprovalEventFromLog(log));
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public static List<TransferEventResponse> getTransferEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static TransferEventResponse getTransferEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TRANSFER_EVENT, log);
        TransferEventResponse typedResponse = new TransferEventResponse();
        typedResponse.log = log;
        typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTransferEventFromLog(log));
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> allowance(String _owner, String _spender) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _owner), 
                new org.web3j.abi.datatypes.Address(160, _spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String _owner) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger _value) {
        final Function function = new Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> burnFrom(String _from, BigInteger _value) {
        final Function function = new Function(
                FUNC_BURNFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _from), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String _from, String _to,
            BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _from), 
                new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static LCToken load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new LCToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static LCToken load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new LCToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static LCToken load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new LCToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static LCToken load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new LCToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<LCToken> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(LCToken.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<LCToken> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(LCToken.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<LCToken> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(LCToken.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<LCToken> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(LCToken.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String _owner;

        public String _spender;

        public BigInteger _value;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String _from;

        public String _to;

        public BigInteger _value;
    }
}
