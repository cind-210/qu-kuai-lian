// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract LCToken {
    // 代币基本信息
    string private _name;
    string private _symbol;
    uint8 private _decimals;
    uint256 private _totalSupply;
    
    // 代币余额映射
    mapping(address => uint256) private _balances;
    // 授权额度映射
    mapping(address => mapping(address => uint256)) private _allowances;
    
    // 事件定义
    event Transfer(address indexed _from, address indexed _to, uint256 _value);
    event Approval(address indexed _owner, address indexed _spender, uint256 _value);
    
    // 构造函数
    constructor() {
        _name = "LCToken";
        _symbol = "LCT";
        _decimals = 18;
        _totalSupply = 0;
    }
    
    // ERC20标准接口实现
    function name() public view returns (string memory) {
        return _name;
    }
    
    function symbol() public view returns (string memory) {
        return _symbol;
    }
    
    function decimals() public view returns (uint8) {
        return _decimals;
    }
    
    function totalSupply() public view returns (uint256) {
        return _totalSupply;
    }
    
    function balanceOf(address _owner) public view returns (uint256 balance) {
        return _balances[_owner];
    }
    
    function transfer(address _to, uint256 _value) public returns (bool success) {
        require(_to != address(0), "Invalid recipient address");
        require(_balances[msg.sender] >= _value, "Insufficient balance");
        
        _balances[msg.sender] -= _value;
        _balances[_to] += _value;
        
        emit Transfer(msg.sender, _to, _value);
        return true;
    }
    
    function transferFrom(address _from, address _to, uint256 _value) public returns (bool success) {
        require(_from != address(0), "Invalid sender address");
        require(_to != address(0), "Invalid recipient address");
        require(_balances[_from] >= _value, "Insufficient balance");
        require(_allowances[_from][msg.sender] >= _value, "Allowance exceeded");
        
        _balances[_from] -= _value;
        _balances[_to] += _value;
        _allowances[_from][msg.sender] -= _value;
        
        emit Transfer(_from, _to, _value);
        return true;
    }
    
    function approve(address _spender, uint256 _value) public returns (bool success) {
        require(_spender != address(0), "Invalid spender address");
        
        _allowances[msg.sender][_spender] = _value;
        
        emit Approval(msg.sender, _spender, _value);
        return true;
    }
    
    function allowance(address _owner, address _spender) public view returns (uint256 remaining) {
        return _allowances[_owner][_spender];
    }
    
    // 扩展功能：发行资产
    function mint(address _to, uint256 _value) public returns (bool success) {
        require(_to != address(0), "Invalid recipient address");
        require(_value > 0, "Mint value must be positive");
        
        _totalSupply += _value;
        _balances[_to] += _value;
        
        emit Transfer(address(0), _to, _value);
        return true;
    }
    
    // 扩展功能：销毁资产
    function burn(uint256 _value) public returns (bool success) {
        require(_balances[msg.sender] >= _value, "Insufficient balance to burn");
        require(_value > 0, "Burn value must be positive");
        
        _balances[msg.sender] -= _value;
        _totalSupply -= _value;
        
        emit Transfer(msg.sender, address(0), _value);
        return true;
    }
    
    // 扩展功能：从指定地址销毁资产
    function burnFrom(address _from, uint256 _value) public returns (bool success) {
        require(_from != address(0), "Invalid sender address");
        require(_balances[_from] >= _value, "Insufficient balance to burn");
        require(_allowances[_from][msg.sender] >= _value, "Allowance exceeded");
        require(_value > 0, "Burn value must be positive");
        
        _balances[_from] -= _value;
        _allowances[_from][msg.sender] -= _value;
        _totalSupply -= _value;
        
        emit Transfer(_from, address(0), _value);
        return true;
    }

}