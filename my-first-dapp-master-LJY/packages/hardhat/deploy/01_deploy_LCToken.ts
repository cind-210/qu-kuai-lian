import { HardhatRuntimeEnvironment } from "hardhat/types";
import { DeployFunction } from "hardhat-deploy/types";
import { Contract } from "ethers";

const deployLCToken: DeployFunction = async function (hre: HardhatRuntimeEnvironment) {
  const { deployer } = await hre.getNamedAccounts();
  const { deploy } = hre.deployments;

  console.log(`Deploying LCToken contract with deployer: ${deployer}`);


  await deploy("LCToken", {
    from: deployer,

    log: true,
    autoMine: true,
  });

  const lcToken = await hre.ethers.getContract<Contract>("LCToken", deployer);
  console.log("👋 Token name:", await lcToken.name());
  console.log("👋 Token symbol:", await lcToken.symbol());
};

export default deployLCToken;

deployLCToken.tags = ["ERC20LC202330550991"];